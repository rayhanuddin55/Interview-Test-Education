package com.rayhan.interviewtesteducation.listeners


/**
 * Generic interface for all list item click
 */
interface ListItemClick<T> {
    fun onListitemClick(item: T)
}