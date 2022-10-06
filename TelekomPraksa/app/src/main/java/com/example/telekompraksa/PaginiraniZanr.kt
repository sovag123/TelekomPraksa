package layout

import android.os.Parcelable
import com.example.telekompraksa.KategorijaModel
import com.example.telekompraksa.ZanrModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaginiraniZanr(val current_page:Int,
                          val data: ArrayList<ZanrModel>
): Parcelable {}
