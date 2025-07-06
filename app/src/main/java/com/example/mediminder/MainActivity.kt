package com.example.mediminder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Routes.RegisterScreen, builder = {
                    composable(Routes.RegisterScreen){
                        Register(navController)
                    }
                    composable(Routes.LoginScreen){
                        Login(navController)
                    }
                    composable(Routes.HomePageWithArg) {
                        val name = it.arguments?.getString("name")
                        val email = it.arguments?.getString("email")
                        val age = it.arguments?.getString("age")
                        val mobileNumber = it.arguments?.getString("mobileNumber")
                        HomePage(navController,
                                 name ?: "Guest",
                                 email ?:"unknown",
                                 age ?: "0",
                            mobileNumber ?: "000")
                    }

                    composable(Routes.ProfileWithArg){
                        val name = it.arguments?.getString("name")
                        val email = it.arguments?.getString("email")
                        val age =  it.arguments?.getString("age")
                        val mobileNumber = it.arguments?.getString("mobileNumber")
                        Profile(navController,
                            name ?: "Guest",
                            email ?:"unknown",
                            age ?: "0",
                            mobileNumber ?: "000")
                    }
                    composable(Routes.MedicalAddScreen){
                        MedicalReports(navController)
                    }
                    composable(Routes.ForgotPass){
                        ForgotPassScreen(navController)
                    }
                })


        }
    }
}