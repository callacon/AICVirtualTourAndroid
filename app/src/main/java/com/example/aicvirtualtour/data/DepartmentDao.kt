package com.example.aicvirtualtour.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aicvirtualtour.models.Department
import com.example.aicvirtualtour.models.Departments

@Dao
interface DepartmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(departments: List<Department>)

    @Query("SELECT * FROM departments")
    suspend fun get(): List<Department>
}