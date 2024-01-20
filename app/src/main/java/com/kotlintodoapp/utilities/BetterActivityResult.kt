package com.kotlintodoapp.utilities

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class BetterActivityResult<Input, Result> private constructor(
    caller: ActivityResultCaller,
    contract: ActivityResultContract<Input, Result>,
    private var onActivityResult: OnActivityResult<Result>?
) {

    private var launcher: ActivityResultLauncher<Input>? = null

    init {
        launcher = caller.registerForActivityResult(contract, this::callOnActivityResult)
    }

    companion object {

        /**
         * Register activity result using a [ActivityResultContract] and an in-place activity result callback like
         * the default approach. You can still customise callback using [.launch].
         */
        fun <Input, Result> registerForActivityResult(
            caller: ActivityResultCaller,
            contract: ActivityResultContract<Input, Result>,
            onActivityResult: OnActivityResult<Result>?
        ): BetterActivityResult<Input, Result> {
            return BetterActivityResult(caller, contract, onActivityResult)
        }

        /**
         * Same as [.registerForActivityResult] except
         * the last argument is set to `null`.
         */
        fun <Input, Result> registerForActivityResult(
            caller: ActivityResultCaller,
            contract: ActivityResultContract<Input, Result>
        ): BetterActivityResult<Input, Result> {
            return registerForActivityResult(caller, contract, null)
        }

        /**
         * Specialised method for launching new activities.
         */
        fun registerActivityForResult(
            caller: ActivityResultCaller
        ): BetterActivityResult<Intent, ActivityResult> {
            return registerForActivityResult(
                caller,
                ActivityResultContracts.StartActivityForResult()
            )
        }

    }

    /**
     * Callback interface
     */
    fun interface OnActivityResult<O> {
        /**
         * Called after receiving a result from the target activity
         */
        fun onActivityResult(result: O)
    }

    fun setOnActivityResult(onActivityResult: OnActivityResult<Result>?) {
        this.onActivityResult = onActivityResult
    }

    /**
     * Launch activity, same as [ActivityResultLauncher.launch] except that it allows a callback
     * executed after receiving a result from the target activity.
     */
    fun launch(input: Input, onActivityResult: OnActivityResult<Result>?) {
        if (onActivityResult != null) {
            this.onActivityResult = onActivityResult
        }
        launcher!!.launch(input)
    }

    /**
     * Same as [.launch] with last parameter set to `null`.
     */
    fun launch(input: Input) {
        launch(input, onActivityResult)
    }

    private fun callOnActivityResult(result: Result) {
        onActivityResult?.onActivityResult(result)
    }

}