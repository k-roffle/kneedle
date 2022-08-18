package com.knitting.kneedle.core.infra.repository.draftdesign

import com.knitting.kneedle.core.usecase.design.DesignService
import com.knitting.kneedle.core.usecase.design.DraftDesignService

interface DraftDesignRepository : DesignService.DraftDesignRepository, DraftDesignService.DraftDesignRepository
