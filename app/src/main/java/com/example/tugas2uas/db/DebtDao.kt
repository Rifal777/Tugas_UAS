package com.example.tugas2uas.db
import androidx.room.*
import com.example.tugas2uas.model.Debt

@Dao
interface DebtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(debt: Debt)

    @Update
    fun update(debt: Debt)

    @Delete
    fun delete(debt: Debt)

    @Query("SELECT * FROM debt")
    fun getAll() : MutableList<Debt>

    @Query("SELECT * FROM debt WHERE id = :id")
    fun getById(id: Int) : Debt
}