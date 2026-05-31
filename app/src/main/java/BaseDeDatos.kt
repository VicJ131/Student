package com.example.student

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "sesiones_estudio")
data class SesionEstudio(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ramo: String,
    val horas: Int,
    val minutos: Int,
    val horaInicio: String?,
    val horaTermino: String?,
    val fecha: Long = System.currentTimeMillis()
)

@Dao
interface SesionDao {

    @Insert
    fun insertarSesion(sesion: SesionEstudio)

    @Query("SELECT * FROM sesiones_estudio")
    fun obtenerTodasLasSesiones(): Flow<List<SesionEstudio>>
}

@Database(entities = [SesionEstudio::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sesionDao(): SesionDao
}