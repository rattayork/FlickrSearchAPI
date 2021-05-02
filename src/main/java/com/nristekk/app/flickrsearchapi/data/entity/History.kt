package com.nristekk.app.flickrsearchapi.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/*
Data class represent history search keyword entity
 */
@Entity(tableName = "history")
data class History(@PrimaryKey @ColumnInfo(name = "keyword") val keyword: String)