package com.knitting.kneedle.core.infra.repository.design

import com.knitting.kneedle.core.usecase.design.DesignService
import com.knitting.kneedle.core.usecase.design.DraftDesignService
import com.knitting.kneedle.core.usecase.summary.ProfileSummaryService

interface DesignRepository :
    DesignService.DesignRepository,
    DraftDesignService.DesignRepository,
    ProfileSummaryService.DesignRepository
