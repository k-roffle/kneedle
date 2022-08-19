package com.knitting.kneedle.core.infra.repository.knitter

import com.knitting.kneedle.core.usecase.auth.AuthService
import com.knitting.kneedle.core.usecase.knitter.KnitterService

interface KnitterRepository : AuthService.KnitterRepository, KnitterService.KnitterRepository
