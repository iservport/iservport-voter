package com.iservport.doc.api

import com.fasterxml.jackson.annotation.JsonIgnore

case class Voter @JsonIgnore() (voterId: String, voterName: String, voted: Int)