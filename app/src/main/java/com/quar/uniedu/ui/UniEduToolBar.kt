package com.quar.uniedu.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.quar.uniedu.databinding.UniEduToolBarBinding

enum class ToolbarEvent {
    HOME, LANG, CONFIG, EXIT
}

class UniEduToolBar(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    private var binding = UniEduToolBarBinding.inflate(LayoutInflater.from(context), this)

    private var onClickEvent: OnOptionSelected? = null

    init {
        initializationAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializationAttributes(
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {

        binding.apply {
            btnHome.setOnClickListener { onClickEvent?.onSelected(ToolbarEvent.HOME) }
            btnLang.setOnClickListener { onClickEvent?.onSelected(ToolbarEvent.LANG) }
            btnConfig.setOnClickListener { onClickEvent?.onSelected(ToolbarEvent.CONFIG) }
            btnExit.setOnClickListener { onClickEvent?.onSelected(ToolbarEvent.EXIT) }
        }

    }

    fun setListener(onOptionSelected: OnOptionSelected) {
        this.onClickEvent = onOptionSelected
    }

}

interface OnOptionSelected {
    fun onSelected(event: ToolbarEvent)
}