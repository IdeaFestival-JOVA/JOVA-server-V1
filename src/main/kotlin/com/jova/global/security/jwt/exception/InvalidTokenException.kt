package com.jova.global.security.jwt.exception

import com.jova.global.exception.ErrorCode
import com.jova.global.exception.JovaException

class InvalidTokenException : JovaException(ErrorCode.INVALID_TOKEN)