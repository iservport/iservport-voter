package com.iservport.doc.impl

import com.iservport.doc.api.{Document, Voter}

case class DocumentState(document: Option[Document], upVoted: Int = 0, downVoted: Int = 0) {

  def vote(voter: Voter): DocumentState = document match {
    case None => throw new IllegalStateException("Can't vote before document is created")
    case Some(d) if voter.voted >0 => copy(upVoted = upVoted + 1)
    case Some(d) if voter.voted <0 => copy(downVoted = downVoted - 1)
    case _ => this
  }
}

object DocumentState { def apply(doc: Document): DocumentState = DocumentState(Option(doc)) }