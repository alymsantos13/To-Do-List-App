package ph.edu.dlsu.mobdeve.santos.alyssa.mc01.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Task(
    var _id: Long?,
    val name: String,
    val description: String,
    val dueDate: Date?,
    val repeat: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as? Long,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        (parcel.readSerializable() as? Long)?.let { Date(it) },
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeSerializable(_id)
        writeString(name)
        writeString(description)
        writeSerializable(dueDate?.time)
        writeByte(if (repeat) 1 else 0)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}