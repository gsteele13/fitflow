package com.fitflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fitflow.ui.ScheduleScreen
import com.fitflow.ui.PlansScreen
import com.fitflow.ui.PlanDetailsScreen
import com.fitflow.ui.HistoryScreen
import com.fitflow.ui.theme.FitFlowTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFlowTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showAboutDialog by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text("Schedule") },
                    selected = false,
                    onClick = {
                        navController.navigate("schedule")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Plans") },
                    selected = false,
                    onClick = {
                        navController.navigate("plans")
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("History") },
                    selected = false,
                    onClick = {
                        navController.navigate("history")
                        scope.launch { drawerState.close() }
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                NavigationDrawerItem(
                    label = { Text("About build") },
                    selected = false,
                    onClick = {
                        showAboutDialog = true
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        if (showAboutDialog) {
            AlertDialog(
                onDismissRequest = { showAboutDialog = false },
                title = { Text("About Build") },
                text = { Text("Build Timestamp: ${BuildConfig.BUILD_TIME}") },
                confirmButton = {
                    TextButton(onClick = { showAboutDialog = false }) { Text("OK") }
                }
            )
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("FitFlow") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Text("☰") // Hamburger icon
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "schedule",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("schedule") { ScheduleScreen() }
                composable("plans") { PlansScreen(onPlanClick = { planId -> navController.navigate("plan_details/$planId") }) }
                composable(
                    "plan_details/{planId}",
                    arguments = listOf(navArgument("planId") { type = NavType.LongType })
                ) { backStackEntry ->
                    val planId = backStackEntry.arguments?.getLong("planId") ?: 0L
                    PlanDetailsScreen(planId = planId, onBack = { navController.popBackStack() })
                }
                composable("history") { HistoryScreen() }
            }
        }
    }
}
