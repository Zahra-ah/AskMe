import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.BufferedWriter
fun main() {
    val fileName = "Clients.txt"
    val file = File(fileName)
    val isCreated = file.createNewFile()
    if (!isCreated)
    {
        println("File already exists or cannot be created.")
    } else
    {
        println("File created successfully.")
    }

    val file_Name="Quetions.txt"
    val _file=File(file_Name)
    val is_Created=_file.createNewFile()
    if(!is_Created)
    {
        println("File already exists or cannot be created.")
    }
    else
    {
        println("File created successfully.")
    }

    val file__Name="Answers.txt"
    val __file=File(file__Name)
    val is__Created=__file.createNewFile()
    if(!is__Created)
    {
        println("File already exists or cannot be created.")
    }
    else
    {
        println("File created successfully.")
    }

    val File_Name="Feed.txt"
    val file_=File(File_Name)
    val iS_Created=file_.createNewFile()
    if(!iS_Created)
    {
        println("File already exists or cannot be created.")
    }
    else
    {
        println("File created successfully.")
    }
    var s=Sign_Up(file)
    var lo=Login_operations( file,_file,__file, file_)
  var  choice:Int
  while(true)
  {

   var  choice:Int
  Menu_Of_SignUp_And_Login()
  println("Enter your choice : ")
  choice= readln().toInt()

        if(choice==3)
        {
            break
        }
        if (choice == 1)
        {
            var l = Log_In(file)
            l.login_fun()
            var flag=true
while(true)
{
    if(l.counter==3)
    {

//        Menu_of_login_operations()
        println("Enter the number of login operation : ")
        var number: Int = readln().toInt()
        if (number == 9)
        {
            break
        }
        if (number == 1) {
            lo.ADD_QS_TO_ME()
        } else if (number == 2) {
            lo.ADD_QS_FROM_ME()
        } else if (number == 3) {
            lo.ADD_ANSWER_QUESTION()
        } else if (number == 4) {
            lo.DELETE_QUESTION()
        } else if (number == 5) {
            lo.ASK_QUESTION()
        } else if (number == 6) {
            lo.LIST_OF_Clients()
        } else if (number == 7) {
            lo.FEED()
        } else if (number == 8) {
            lo.LOGOUT()
        }
        else if(number>9||number<1)
        {
            println("This number is out of required range\nTry again with range 1:9 : ")
        }
    }


}
        }
        else if (choice == 2)
        {
            s.sign_up()
        }
      else if(choice>3||choice<1)
        {
            println("This number is out of required range\nTry again with range 1:3 : ")
        }
    }

    }
data class Client(var Id: Int, var name: String, var password: String, var password_confirmation: String, var email: String)
{

}
data class Question_to_me(var Id_from_user:Int,var Id:Int,var qs:String)
{

}

data class Question_from_me(var Id_to_user:Int,var Id:Int,var qs:String)
{

}
data class Question(var id_User:Int,var id_qs:Int,var qs:String)
{

}
class Sign_Up(val file: File)
{

    fun sign_up()
    {
        var id:Int
        var name: String
        var password: String
        var password_confirmation: String
        var email: String
        println("Enter Your id ")
        id= readln().toInt()
        println("Enter your name")
        name = readLine().toString()
        println("Enter your email")
        email = readLine().toString()
        println("Enter your password")
        password = readLine().toString()
        while (true) {

            if (password.length <= 8)
            {
                println("Your password should be at least 8 characters long")
                println("Enter New password")
                password = readLine().toString()
            }
            else
            {
                break
            }
        }

        println("Password confirmation")
        password_confirmation = readLine().toString()
        while (true)
        {
            if (password != password_confirmation)
            {
                println("Passwords do not match")
                println("Try again:")
                password_confirmation = readLine().toString()
            }
            else
            {
                break
            }
        }
        var New = Client(id, name, password, password_confirmation, email)

        file.appendText("$id\n")
        file.appendText("$name\n")
        file.appendText("$email\n")
        file.appendText("$password\n")
        file.appendText("$password_confirmation\n")
        println("Hellow,you are new Client")

    }
}


class Log_In(val file:File)
{
    var counter: Int = 0
    private var email:String=""
    private var  password:String =""
    private var password_conformation:String=""
   init
   {
println("Enter your email : ")
       email= readln().toString()
println("Enter your password : ")
       password= readln().toString()
println("Confirm password : ")
       password_conformation= readln().toString()

   }

 fun login_fun  ()
 {


     file.forEachLine{

         if (email == it || password == it || password_conformation == it)
         {
             counter++
         }
     }
         if (counter == 3)
         {
             println("hello,This client already exists")
             Menu_of_login_operations()

         }
         else if(counter!=3)
         {
             println("This Client not found, try again and if you don't have an account ,please sign up ")
         }

     }

};
fun Menu_Of_SignUp_And_Login()
{
    println("Menu : ")
    println("   Please,press 1 to login")
    println("   Please,press 2 to sign up")
    println("   Please,press 3 to exit ")

}
fun Menu_of_login_operations()
{
   println("Menu : ")
    print(  "   1:Add question to me\n" +
            "   2:Add question from me\n" +
            "   3:answer question\n" +
            "   4:delete question\n" +
            "   5:Ask question\n" +
            "   6:List System users\n" +
            "   7:Feed\n" +
            "   8:Logout\n" +
            "   9:exit\n"
         )
}
class Login_operations(val file:File,val _file:File,val __file:File,val file_:File)
{
   private var Questions_to_me= arrayListOf<Question_to_me>()
    private var Questions_from_me = arrayListOf<Question_from_me>()
    private var map = hashMapOf<Question, String>()
    private var get_file_data = arrayListOf<Question>()
    private var ASk_Question= arrayListOf<Question>()
    fun ADD_QS_TO_ME()//this function print question to me in file and console
    {
        var cn: Int = 0;
        println("Enter your question to me : ")
        var Question_String: String = readln().toString()
        println(" Enter the Sender Client Id : ")
        var person_id = readln().toInt()
        println("Enter your question id : ")
        var Question_id: Int = readln().toInt()
        file.forEachLine {
            if (person_id.toString() == it)
            {
                cn++
            }
        }

        if (cn != 0)
        {

            var new_qs = Question_to_me(person_id, Question_id, Question_String)
            Questions_to_me.add(new_qs)

            Questions_to_me.forEach { Question_to_me ->
                println("The question ID ( ${Question_to_me.Id}) from Client ID (${Question_to_me.Id_from_user})      The Question : ${Question_to_me.qs}")
                _file.appendText("${Question_to_me.Id_from_user}\n")
                _file.appendText("${Question_to_me.Id}\n")
                _file.appendText("${Question_to_me.qs}?\n")
            }
        }
        else
        {
            println("This Client is not found and can't ask any question ")
        }
    }
    fun ADD_QS_FROM_ME()//this function print question of me in file and console
    {
        var cn: Int = 0
        println("Enter the Sender Client Id : ")
        var id: Int = readln().toInt()
        println("Enter question Id : ")
        var id_Qs = readln().toInt()
        println("Enter the Question : ")
        var Qs = readln().toString()
        file.forEachLine {
            if (id.toString() == it)
            {
                cn++
            }
        }
        if (cn != 0) {
            var New_qs = Question_from_me(id, id_Qs, Qs)
            Questions_from_me.add(New_qs)
            Questions_from_me.forEach { Question_from_me ->
                println("The question ID (${Question_from_me.Id}) to the Client ID (${Question_from_me.Id_to_user})      The Question : ${Question_from_me.qs}")
                _file.appendText("${Question_from_me.Id_to_user}\n")
                _file.appendText("${Question_from_me.Id}\n")
                _file.appendText("${Question_from_me.qs}?\n")
            }
        }
        else
        {
            println("This Client is not found and can't ask any question ")
        }

    }
    fun ADD_ANSWER_QUESTION() //this function check all questions and answer the required question in system
    {
        println("Enter the question id or -1 to cancel ")
        var id = readln().toInt()
        val reader = BufferedReader(FileReader(_file))
if(id==-1)
{
    return
}
      else
        {

    while (true)
    {
        val idString = reader.readLine()
        if (idString == null)
        {
            break
        }
        val id_user = idString.toInt()
        if (id_user == null)
        {
            break
        }
        val id_String=reader.readLine()
        val idQs=id_String.toInt()
        if(idQs==null)
        {
            break
        }
        val qs=reader.readLine()
        if(qs==null)
        {
            break
        }
        val data_qs = Question(id_user, idQs, qs)
        get_file_data.add(data_qs)
    }
    var flag:Boolean=false
    var answer: String
    get_file_data.forEach { data_qs ->
        if (id == data_qs.id_qs)
        {
            flag =true
            println(
                "Question Id (${data_qs.id_qs}) from Client id(${data_qs.id_User})\n" +
                        "Question : ${data_qs.qs}\n"
            )
            print("Enter answer: ")
            answer = readln().toString()
            map.put(data_qs, "${answer}")
            __file.appendText("${answer}\n")


        }

    }
    if(flag==false)
    {
        println("Invalid Question id")
    }
    for (i in map)
    {
        file_.appendText(
            "Question Id (${i.key.id_qs}) from Client id(${i.key.id_User})\n" +
                    "Question : ${i.key.qs}\n" +
                    "Answer : ${i.value}\n"
        )
    }
    reader.close()
}
    }

    fun FEED()//this function print all answered questions with their answers in system
    {
file_.forEachLine {
    println(it)
   }
    }
    fun DELETE_QUESTION()//to delete question in system
    {
        var cn:Int=0
println("Enter the  Sender Client id ")
var id_client_toDelete :String= readln().toString()
println("Enter the question id ")
var id_Qs_toDelete:String= readln().toString()
println("Enter the Question ")
var Qs_toDelete:String= readln().toString()

        var tempFile = File.createTempFile("temp", null)
        var reader = BufferedReader(FileReader(_file))
        var writer = BufferedWriter(FileWriter(tempFile))

            _file.forEachLine {
                if(id_client_toDelete.toString()==it||id_Qs_toDelete.toString()==it||Qs_toDelete==it)
                {
                    cn++
                }
            }


            if(cn!=0) {
                var curr: String?

                while (reader.readLine().also { curr = it } != null) {
                    var idClient = curr
                    var idQs = reader.readLine()
                    var QS = reader.readLine()
                    if (idClient != id_client_toDelete || idQs != id_Qs_toDelete || QS != Qs_toDelete) {
                        writer.write(idClient)
                        writer.newLine()
                        writer.write(idQs)
                        writer.newLine()
                        writer.write(QS)
                        writer.newLine()
                    }
                }
                writer.close()
                reader.close()
                _file.delete()
                tempFile.renameTo(_file)
            }
            else
            {
                println("this question don't exist ")
            }
    }
    fun ASK_QUESTION()// this function to ask some question and add it in file system
    {
      println("Enter the question id or -1 to cancel")
        var Id: Int = readln().toInt()
        if(Id==-1)
        {
            return
        }
        else
        {
            println("Enter your Question Id")
            var Id_Qs: Int = readln().toInt()
            println("Enter Your Question")
            var Qs: String = readln().toString()
            var cn: Int = 0
            file.forEachLine {
                if (Id.toString() == it) {
                    cn++
                }
            }
            if (cn == 1)
            {
                var New_Qs = Question(Id, Id_Qs, Qs)
                ASk_Question.add(New_Qs)
                ASk_Question.forEach { Question ->
                    println(
                        "Question Id (${Question.id_qs}) from Client id(${Question.id_User})\n" +
                                "Question : ${Question.qs}\n"
                    )
                    _file.appendText("${Question.id_User}\n")
                    _file.appendText("${Question.id_qs}\n")
                    _file.appendText("${Question.qs}?\n")

                }
            }
            else if (cn == 0)
            {
                println("invalid Id")
            }
        }
    }
        fun LIST_OF_Clients()//this function to print all clients or users that store in system with id and name
        {
            var list_of_users = arrayListOf<Client>()
            val reader = BufferedReader(FileReader(file))
            while (true) {
                var idString = reader.readLine()
                if (idString == null) {
                    break
                }
                var id = idString.toInt()
                if (id == null) {
                    break
                }
                var name = reader.readLine()
                if (name == null) {
                    break
                }

                var email = reader.readLine()
                if (email == null) {
                    break
                }
                var password = reader.readLine()
                if (password == null) {
                    break
                }

                var password_confirmation = reader.readLine()
                if (password_confirmation == null) {
                    break
                }


                var client = Client(id, name, password, password_confirmation, email)
                list_of_users.add(client)
            }
            reader.close()
            list_of_users.forEach { client ->
                println("Id: ${client.Id},    Name: ${client.name}")
            }
        }
        fun LOGOUT() //this function to logout client in system
        {
            println("Enter your id ")
            var id_To_Delete = readLine().toString()
            println("Enter your name")
            var name_To_Delete = readLine().toString()
            println("Enter your email")
            var email_To_Delete = readLine().toString()
            println("Enter your password")
            var password_To_Delete = readLine().toString()
            println("Enter your password_confirmation")
            var password_Confirmation_To_Delete = readLine().toString()

            var tempFile = File.createTempFile("temp", null)
            var reader = BufferedReader(FileReader(file))
            var writer = BufferedWriter(FileWriter(tempFile))

            var curr: String?

            while (reader.readLine().also { curr = it } != null)
            {
                var id = curr
                var name = reader.readLine()
                var email = reader.readLine()
                var password = reader.readLine()
                var passwordConfirmation = reader.readLine()

                if (id != id_To_Delete || name != name_To_Delete || email != email_To_Delete ||
                    password != password_To_Delete || passwordConfirmation != password_Confirmation_To_Delete
                )
                {
                    writer.write("$id\n")
                    writer.write("$name\n")
                    writer.write("$email\n")
                    writer.write("$password\n")
                    writer.write("$passwordConfirmation\n")
                }
            }
            writer.close()
            reader.close()
            file.delete()
            tempFile.renameTo(file)
            println("Successful logout process")

        }
};
/* the content of clients.txt file
1
noor
no@gmail.com
123456789
123456789
2
fatma
fa@gmail.com
1212^1212^
1212^1212^
3
saleh
sa@gmail.com
12345671234567
12345671234567
 */

/* the content of Questions.txt file
1
1
what is the best java or kotlin?
3
2
what is your favourite language?
1
3
what is your company position?
*/

/* the content of Answer.txt file
i think kotlin
*/

/* the content of Feed.txt file
Question Id (1) from Client id(1)
Question : what is the best java or kotlin?
Answer : I think kotlin
*/
