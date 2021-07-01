package com.vt.extendedbeaglelib.config

import br.com.zup.beagle.analytics.Analytics
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.action.FormLocalActionHandler
import br.com.zup.beagle.android.components.form.core.ValidatorHandler
import br.com.zup.beagle.android.data.serializer.adapter.generic.TypeAdapterResolver
import br.com.zup.beagle.android.imagedownloader.BeagleImageDownloader
import br.com.zup.beagle.android.logger.BeagleLogger
import br.com.zup.beagle.android.navigation.BeagleControllerReference
import br.com.zup.beagle.android.navigation.DeepLinkHandler
import br.com.zup.beagle.android.networking.HttpClient
import br.com.zup.beagle.android.networking.HttpClientFactory
import br.com.zup.beagle.android.networking.urlbuilder.UrlBuilder
import br.com.zup.beagle.android.operation.Operation
import br.com.zup.beagle.android.setup.BeagleConfig
import br.com.zup.beagle.android.setup.BeagleSdk
import br.com.zup.beagle.android.setup.DesignSystem
import br.com.zup.beagle.android.store.StoreHandler
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.newanalytics.AnalyticsProvider

public class MyBeagleSetup(val widgets: List<Class<WidgetView>>) : BeagleSdk {
    public override val formLocalActionHandler: FormLocalActionHandler? = null

    public override val deepLinkHandler: DeepLinkHandler =
        com.vt.extendedbeaglelib.config.AppDeepLinkHandler()

    public override val httpClient: HttpClient =
        com.vt.extendedbeaglelib.config.httpclient.HttpClientDefault()

    public override val httpClientFactory: HttpClientFactory? = null

    public override val designSystem: DesignSystem = com.vt.extendedbeaglelib.config.AppDesignSystem()

    public override val storeHandler: StoreHandler? = null

    public override val urlBuilder: UrlBuilder? = null

    public override val analytics: Analytics? = null

    public override val analyticsProvider: AnalyticsProvider? = null

    public override val logger: BeagleLogger =
        com.vt.extendedbeaglelib.config.cache.BeagleLoggerDefault()

    public override val controllerReference: BeagleControllerReference =
        ControllerReferenceGenerated()

    public override val imageDownloader: BeagleImageDownloader? = null

    public override val serverDrivenActivity: Class<BeagleActivity> =
        br.com.zup.beagle.android.view.ServerDrivenActivity::class.java as Class<BeagleActivity>

    public override val config: BeagleConfig = AppBeagleConfig()

    public override val typeAdapterResolver: TypeAdapterResolver = RegisteredCustomTypeAdapter

    public override val validatorHandler: ValidatorHandler = RegisteredCustomValidator

    public override fun registeredWidgets(): List<Class<WidgetView>> =
        merge(RegisteredWidgets.registeredWidgets(), widgets)

    public override fun registeredOperations(): Map<String, Operation> =
        RegisteredOperations.registeredOperations()

    public override fun registeredActions(): List<Class<Action>> =
        RegisteredActions.registeredActions()
}


fun <T> merge(first: List<T>, second: List<T>): List<T> {
    val list: MutableList<T> = ArrayList()
    list.addAll(first)
    list.addAll(second)
    return list
}
