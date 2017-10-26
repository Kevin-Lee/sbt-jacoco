package com.github.sbt.jacoco.coveralls

import java.io.File

import com.github.sbt.jacoco.report.formats.JacocoReportFormat
import org.jacoco.report.IReportVisitor
import sbt._

class CoverallsReportFormat(sourceDirs: Seq[File], projectRootDir: File, jobId: String, repoToken: Option[String])
    extends JacocoReportFormat {

  override def createVisitor(directory: File, encoding: String): IReportVisitor = {
    IO.createDirectory(directory)

    new CoverallsReportVisitor(directory / "coveralls.json", sourceDirs, projectRootDir, jobId, repoToken)
  }
}
