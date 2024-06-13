package com.example.pr18

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException


class TascActivity : AppCompatActivity() {
    private lateinit var EdText: EditText
    private lateinit var addButton: Button
    private lateinit var LText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasc)
        EdText = findViewById<EditText>(R.id.EditT)
        addButton = findViewById<Button>(R.id.addButton)
        LText = findViewById<TextView>(R.id.List)
        addButton.setOnClickListener(View.OnClickListener {
            val todo = EdText.getText().toString()
            if (!todo.isEmpty()) {
                addTodo(todo)
                EdText.setText("")
            }
            else
                Toast.makeText(this, "Введите задачу", Toast.LENGTH_SHORT).show()
        })
        displayTodos()
    }

    private fun addTodo(todo: String) {
        val sharedPreferences = getSharedPreferences("TodoList", MODE_PRIVATE)
        try {
            val todoList = JSONArray(sharedPreferences.getString("todos", "[]"))
            todoList.put(todo)
            val editor = sharedPreferences.edit()
            editor.putString("todos", todoList.toString())
            editor.apply()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun displayTodos() {
        val sharedPreferences = getSharedPreferences("TodoList", MODE_PRIVATE)
        try {
            val todoList = JSONArray(sharedPreferences.getString("todos", "[]"))
            val todos = StringBuilder()
            for (i in 0 until todoList.length()) {
                todos.append(todoList.getString(i)).append("\n")
            }
            LText.text = todos.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    fun ava(view: View) {
        startActivity(Intent(this@TascActivity, LoginActivity::class.java))
    }
}