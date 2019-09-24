package inas.anisha.classify.adapter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class TaskDataModel(
    var taskName: String,
    var dueDate: Calendar,
    var description: String,
    var subject: String?
) : Parcelable