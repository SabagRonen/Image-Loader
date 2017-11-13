package com.gettaxi.repository

typealias SuccessResponseHandle = (UseCaseResponse) -> Unit
typealias ErrorResponseHandle = () -> Unit
typealias FatalResponseHandle = () -> Unit

open class UseCaseRequest {
    operator fun invoke(block : UseCaseRequest.() -> Unit) {
        block()
    }
}

open class UseCaseResponse

open class UseCase<Request : UseCaseRequest, Response : UseCaseResponse> {

    lateinit var request: Request
    var runner: Runner = LaunchRunner()
    var suceess: SuccessResponseHandle = {}
    var error: ErrorResponseHandle? = null
    var fatal: FatalResponseHandle? = null

    fun onResponse(block: SuccessResponseHandle) {
        suceess = block
    }

    fun onError(block: () -> Unit) {
        error = block
    }

    fun onFatal(block: () -> Unit) {
        fatal = block
    }

    operator fun invoke(function: UseCase<Request, Response>.() -> Unit) {
        function()
        runner.run {
            handleUseCase()
        }
    }

    protected fun inParallel(block: suspend UseCase<Request, Response>.() -> Unit) {
        runner.run {
            block()
        }
    }

    protected open fun handleUseCase() {

    }
}