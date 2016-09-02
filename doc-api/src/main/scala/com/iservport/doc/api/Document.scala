package com.iservport.doc.api

import java.util.Date

import com.fasterxml.jackson.annotation.JsonIgnore

case class Document @JsonIgnore() (id: String, entityId: String, docCode: String, docName: String, docAbstract: String,
  issueDate: Date, authorId: String, authorName: String, docType: Char, docContent: String, voters: Seq[Voter])
