package com.example.primefinderclient.primefinderclient

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import java.io.DataOutputStream
import java.io.ObjectInputStream
import java.net.InetAddress
import java.net.Socket


class HelloController {
    @FXML
    lateinit var outputTextArea: TextArea

    @FXML
    lateinit var inputTextField: TextField

    @FXML
    private lateinit var welcomeText: Label

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"
    }

    fun onInput(actionEvent: ActionEvent) {
        try {

            //  get values from field
            val textIn: String = inputTextField.text
            inputTextField.clear()

            //  setup socket within try catch
            try {
                val socket = Socket(InetAddress.getLocalHost(), 8000)

                //  setup output stream
                val outputStream = socket.getOutputStream()
                val dataOutputStream = DataOutputStream(outputStream)
                println("Sending string to the ServerSocket")

                // write the message we want to send
                dataOutputStream.writeUTF(textIn)
                dataOutputStream.writeUTF("\n")
                dataOutputStream.flush() // send the message
                //                        dataOutputStream.close(); // close the output stream when we're done.
                var flag = true

//                        while (flag != false) {
                //  get response
                val objectInput = ObjectInputStream(socket.getInputStream())
                val raw = objectInput.readObject()
                var received = raw.toString()
                if (received != null) flag = false
                println("Received: \n")

                outputTextArea.appendText("$textIn, is Prime?: $received \n")

                dataOutputStream.close()
                outputStream.close()
                socket.close()
            } catch (ex: Exception) {
                System.out.printf("ERROR!!!---- \n$ex")
            }
        } catch (e1: Exception) {
            println("ONE OR MORE FIELDS ARE BLANK")
            println(e1)
            inputTextField.text = "One or more fields empty, please try again"
        }

    }
}