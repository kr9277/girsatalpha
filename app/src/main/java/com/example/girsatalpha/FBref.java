package com.example.girsatalpha;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;

public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference refUser = FBDB.getReference("User");
    public static FirebaseAuth refAuth =  FirebaseAuth.getInstance();
}

