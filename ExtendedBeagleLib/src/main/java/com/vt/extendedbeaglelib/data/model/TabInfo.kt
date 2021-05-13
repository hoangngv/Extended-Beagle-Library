package com.vt.extendedbeaglelib.data.model

import com.vt.extendedbeaglelib.common.enum_class.TabInfoType

data class TabInfo(
        val remoteIconUrl: String? = null,
        val title: String? = null,
        val api: String? = null,
        val cacheFile: String? = null,
        val type: TabInfoType = TabInfoType.BEAGLE,
        val fileName: String? = null,
        val className: String? = null,
        val storyboardName: String? = null // Just for only iOS
)