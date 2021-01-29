package com.example.google_sign_in_g_29

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Note(
    val title: String = "",
    val status: String = ""
)

class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var rcNotesView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        rcNotesView = findViewById(R.id.rcNotesView)

        val store = Firebase.firestore;

        val notesCollectionQuery = store.collection("notes")
        val options = FirestoreRecyclerOptions.Builder<Note>()
            .setLifecycleOwner(this)
            .setQuery(notesCollectionQuery, Note::class.java).build()



        val adapter = object: FirestoreRecyclerAdapter<Note, NoteViewHolder>(options) {
            override fun onBindViewHolder(holder: NoteViewHolder, position: Int, model: Note) {
                val tvTitle = holder.itemView.findViewById<TextView>(android.R.id.text1)
                val tvStatus = holder.itemView.findViewById<TextView>(android.R.id.text2)

                tvTitle.text = model.title
                tvStatus.text = model.status

                holder.itemView.setOnClickListener {
                    Log.i("RECYCLER_VIEW", model.title)
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
                val view = LayoutInflater.from(this@MainActivity).inflate(android.R.layout.simple_list_item_2, parent, false)

                return NoteViewHolder(view)
            }
        }

        rcNotesView.adapter = adapter;
        rcNotesView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if( item.itemId == R.id.mLogOut ) {
            auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        else if ( item.itemId == R.id.mNewNote) {
            showActionDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showActionDialog() {
        val editText = EditText(this)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Create new Note")
            .setView(editText)
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK", null)
            .show()


        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            Log.i("MAIN_ACTIVITY_TAG", "Clicked on positive button!")

            val stateText = editText.text.toString()
            if (stateText.isBlank()) {
                Toast.makeText(this, "Cannot submit empty text", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val currentUser = auth.currentUser
            if (currentUser == null) {
                Toast.makeText(this, "No signed in user", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = Firebase.firestore

            val note = Note(stateText, "Pending");

            db.collection("notes").add(note).addOnSuccessListener {   newNote ->
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { ex ->
                Log.e("EXCEPTION", ex.message.toString())
            }

            dialog.dismiss()
        }
    }
}