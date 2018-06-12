package acker.brian.ackmath_android.challenge

import acker.brian.ackmath_android.BR
import acker.brian.ackmath_android.R
import android.content.res.Resources
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import java.util.*

/**
 * Created by Brian on 2/6/2017.
 */

class ChallengeViewModel(private val resources: Resources, private val lowerBound: Int, private val upperBound: Int, private val answerText: EditText) : BaseObservable() {
    private var challengeNumber: Int = 0
    private var answerStatus: Int = 0
    private var questionStartTime: Long = 0
    private var answerTime = 0.0

    val successText: String
        @Bindable
        get() {
            when (answerStatus) {
                STATUS_CORRECT -> return resources.getString(R.string.correct)
                STATUS_INCORRECT -> return resources.getString(R.string.incorrect)
                else -> return ""
            }
        }

    val challengeText: String
        @Bindable
        get() = "" + challengeNumber * challengeNumber

    val successVisibility: Int
        @Bindable
        get() = if (answerStatus == STATUS_CORRECT || answerStatus == STATUS_INCORRECT) View.VISIBLE else View.INVISIBLE

    val successTimetext: String
        @Bindable
        get() = resources.getString(R.string.answer_time, answerText, answerTime.toString())

    val successTimeVisibility: Int
        @Bindable
        get() = if (answerStatus == STATUS_CORRECT) View.VISIBLE else View.INVISIBLE

    val buttonText: String
        @Bindable
        get() {
            when (answerStatus) {
                STATUS_CORRECT -> return resources.getString(R.string.new_number)
                STATUS_INCORRECT, STATUS_UNANSWERED -> return resources.getString(R.string.submit_answer)
                else -> return resources.getString(R.string.submit_answer)
            }
        }

    init {
        answerStatus = 0
        randomizeChallengeNumber()
    }

    private fun randomizeChallengeNumber() {
        val random = Random()
        challengeNumber = random.nextInt(upperBound - lowerBound) + lowerBound
        answerText.setText("")
        answerStatus = STATUS_UNANSWERED
        notifyPropertyChanged(BR.challengeText)
        notifyPropertyChanged(BR.successText)
        notifyPropertyChanged(BR.successVisibility)
        notifyPropertyChanged(BR.buttonText)
        notifyPropertyChanged(BR.successTimeVisibility)
        questionStartTime = SystemClock.elapsedRealtime()
    }

    fun onClickSubmit(view: View) {
        if (!TextUtils.isEmpty(answerText.text.toString())) {
            when (answerStatus) {
                STATUS_CORRECT -> randomizeChallengeNumber()
                STATUS_INCORRECT, STATUS_UNANSWERED -> {
                    val answer = Integer.parseInt(answerText.text.toString())
                    answerTime = (SystemClock.elapsedRealtime() - questionStartTime) / 1000.0
                    if (answer == challengeNumber) {
                        answerStatus = STATUS_CORRECT
                    } else {
                        answerStatus = STATUS_INCORRECT
                    }
                    notifyPropertyChanged(BR.successVisibility)
                    notifyPropertyChanged(BR.successText)
                    notifyPropertyChanged(BR.buttonText)
                    notifyPropertyChanged(BR.successTimetext)
                    notifyPropertyChanged(BR.successTimeVisibility)
                }
                else -> {
                    val answer = Integer.parseInt(answerText.text.toString())
                    answerTime = (SystemClock.elapsedRealtime() - questionStartTime) / 1000.0
                    if (answer == challengeNumber) {
                        answerStatus = STATUS_CORRECT
                    } else {
                        answerStatus = STATUS_INCORRECT
                    }
                    notifyPropertyChanged(BR.successVisibility)
                    notifyPropertyChanged(BR.successText)
                    notifyPropertyChanged(BR.buttonText)
                    notifyPropertyChanged(BR.successTimetext)
                    notifyPropertyChanged(BR.successTimeVisibility)
                }
            }
        }
    }

    companion object {
        private const val STATUS_UNANSWERED = 0
        private const val STATUS_INCORRECT = 1
        private const val STATUS_CORRECT = 2
    }
}
