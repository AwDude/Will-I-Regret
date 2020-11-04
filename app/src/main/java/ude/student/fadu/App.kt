package ude.student.fadu

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		initRealm()
	}

	private fun initRealm() {
		Realm.init(this)
		// TODO later a migration may be needed
		val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).build()
		Realm.setDefaultConfiguration(config)
	}
}