package com.github.sbt.jacoco.coveralls

import java.io.{File, FileInputStream}

import sbt.Keys.TaskStreams

import scalaj.http.{Http, MultiPart}

object CoverallsClient {
  private val jobsUrl = "https://coveralls.io/api/v1/jobs"

  def sendReport(reportFile: File, streams: TaskStreams): Unit = {
    val response = Http(jobsUrl)
      .postMulti(
        MultiPart(
          "json_file",
          "json_file.json",
          "application/json",
          new FileInputStream(reportFile),
          reportFile.length(),
          _ => ())
      ).asString
    
    if (response.isSuccess) {
      streams.log.info("Upload complete")
    } else {
      streams.log.error(s"Unexpected response from coveralls: ${response.code}")
    }
  }
}
