package com.starwars.shared.repo

class DataHolder private constructor() {
   /* lateinit var photoResult: ArrayList<Result>
    lateinit var detailphotoResult: Result*/
    lateinit var description: String

    companion object {
        private var sInstance: DataHolder? = null
        private val LOCK = Any()

        val instance: DataHolder
            @Synchronized get() {
                if (sInstance == null) {
                    synchronized(LOCK) {
                        sInstance = DataHolder()
                    }
                }
                return sInstance!!
            }

        fun setsInstance(sInstance: DataHolder) {
            DataHolder.sInstance = sInstance
        }
    }
}
