package com.vt.extendedbeaglelib.utils.bus

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

object SingleBus {
    private val flows = ConcurrentHashMap<SingleBusKey, MutableSharedFlow<Any>>()

    private val senderFlow = MutableSharedFlow<Any>()

    private fun getSender(key : SingleBusKey) : MutableSharedFlow<Any>? {
        return if(flows.containsKey(key)) {
            flows[key]
        } else {
            val flow = MutableSharedFlow<Any>()
            flows[key] = flow
            flows[key]
        }
    }

    @Deprecated("use send(key : String, item: Any)")
    fun send(item: Any) {
        GlobalScope.launch(Dispatchers.Main){
            senderFlow.emit(item)
        }
    }

    @Deprecated("use receive(key : String, action : (Any) -> Unit = {})")
    fun receive(action : (Any) -> Unit = {}) : Job =
        GlobalScope.launch(Dispatchers.Main){
            senderFlow.collect {
                action.invoke(it)
            }
        }

    fun send(key : SingleBusKey, item: Any) {
        GlobalScope.launch(Dispatchers.Main){
            val flow = getSender(key)
            flow?.emit(item)
        }
    }

    fun receive(key : SingleBusKey, action : (Any) -> Unit = {}) : Job =
        GlobalScope.launch(Dispatchers.Main){
            val flow = getSender(key)
            flow?.collect {
                action.invoke(it)
            }
        }
}