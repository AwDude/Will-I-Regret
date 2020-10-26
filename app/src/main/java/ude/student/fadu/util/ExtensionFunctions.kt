@file:Suppress("FunctionName", "unused")

package ude.student.fadu.util

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import io.realm.OrderedRealmCollection
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults
import ude.student.fadu.repo.LiveRealmCollection
import kotlin.reflect.KProperty

fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)

fun <T> RealmQuery<T>.equalTo(field: KProperty<String?>, value: String?): RealmQuery<T> = equalTo(field.name, value)
fun <T> RealmQuery<T>.notEqualTo(field: KProperty<String?>, value: String?): RealmQuery<T> = notEqualTo(field.name, value)

fun Context.getLifecycleOwner(): LifecycleOwner {
	var context = this
	while (context !is LifecycleOwner) {
		context = (context as ContextWrapper).baseContext
	}
	return context
}

fun <T : RealmModel> OrderedRealmCollection<T>.asLive() = LiveRealmCollection(this)

@Suppress("UNCHECKED_CAST")
fun <T : RealmModel> OrderedRealmCollection<T>.addListener(listener: OrderedRealmCollectionChangeListener<*>) {
	if (!isManaged || !isValid) return
	when (this) {
		is RealmResults<T> -> addChangeListener(listener as OrderedRealmCollectionChangeListener<RealmResults<T>>)
		is RealmList<T> -> addChangeListener(listener as OrderedRealmCollectionChangeListener<RealmList<T>>)
		else -> throw ClassCastException("A OrderedRealmCollectionChangeListener can only be added to a OrderedRealmCollection of type RealmResults or RealmList!")
	}
}

@Suppress("UNCHECKED_CAST")
fun <T : RealmModel> OrderedRealmCollection<T>.removeListener(listener: OrderedRealmCollectionChangeListener<*>) {
	if (!isManaged || !isValid) return
	when (this) {
		is RealmResults<T> -> removeChangeListener(listener as OrderedRealmCollectionChangeListener<RealmResults<T>>)
		is RealmList<T> -> removeChangeListener(listener as OrderedRealmCollectionChangeListener<RealmList<T>>)
		else -> throw ClassCastException("A OrderedRealmCollectionChangeListener can only be added to a OrderedRealmCollection of type RealmResults or RealmList!")
	}
}

fun <T> RealmQuery<T>.max(field: KProperty<*>) = max(field.name)