package com.example.aicvirtualtour

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.aicvirtualtour.data.AICDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class MockDatabase {
    private lateinit var _db: AICDatabase
    val db: AICDatabase
        get() = _db

    @Before
    fun initDb() {
        _db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AICDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        _db.close()
    }
}