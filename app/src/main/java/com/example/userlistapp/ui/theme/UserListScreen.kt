package com.example.userlistapp.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.userlistapp.viewmodel.UserUiState
import com.example.userlistapp.viewmodel.UserViewModel

@Composable
fun UserListScreen(viewModel: UserViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UserUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UserUiState.Success -> {
            val users = (uiState as UserUiState.Success).users
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(users) { user ->
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Text(text = user.name, style = MaterialTheme.typography.titleMedium)
                        Text(text = user.email)
                        Text(text = user.company.name, style = MaterialTheme.typography.bodySmall)
                        Divider()
                    }
                }
            }
        }
        is UserUiState.Error -> {
            val message = (uiState as UserUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = message)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.fetchUsers() }) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}
