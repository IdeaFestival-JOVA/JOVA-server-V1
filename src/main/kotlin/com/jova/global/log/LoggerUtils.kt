package com.jova.global.log

import org.slf4j.LoggerFactory

inline fun <reified T> T.listOf() = LoggerFactory.getLogger(T::class.java)!!