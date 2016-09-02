package com.iservport.doc.impl

import com.iservport.doc.api.{Document, Voter}

case class DocumentState(document: Option[Document]) {
  def addVote(voter: Voter): DocumentState = document match {
    case None => throw new IllegalStateException("Can't vote before document is created")
    case Some(d) =>
      val newVoters = d.voters :+ voter
      DocumentState(Some(d.copy(voters = newVoters)))
  }
}

object DocumentState { def apply(doc: Document): DocumentState = DocumentState(Option(doc)) }