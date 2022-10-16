package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: String) {
    val context = textView.context
    textView.text =
        String.format(
            context.getString(R.string.astronomical_unit_format),
            number.toDoubleOrNull() ?: 0.0
        )
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: String) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number.toDoubleOrNull() ?: 0.0)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: String) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number.toDoubleOrNull() ?: 0.0)
}

@BindingAdapter("loadingWheel")
fun goneIfNotNull(view: View, it: Int) {
    view.visibility = if (it != 0) View.GONE else View.VISIBLE
}

@BindingAdapter("pictureUrl")
fun bindUriToImage(imageView: ImageView, strUrl: String?) {
    Picasso.get()
        .load(strUrl)
        .placeholder(R.drawable.placeholder_picture_of_day)
        .error(R.drawable.placeholder_picture_of_day)
        .into(imageView)
}

@BindingAdapter("emptyTextDesc")
fun bindTextViewToEmptyTextDesc(textView: TextView, strTitle: String?) {
    val context = textView.context

    if (strTitle == null) {
        textView.text = context.getString(R.string.image_of_the_day)
    } else {
        textView.text = strTitle
    }
}