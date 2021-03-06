package thall59.assignment6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.text.NumberFormat; // Currency formatter


// This application displays an order form to order coffee from Java Fantasy
public class Coffee extends AppCompatActivity {

    // declaring variables
    int quantity = 0;
    double price = 0.0;
    boolean hasChocolate;
    boolean hasWhippedCream;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        getSupportActionBar().setDisplayShowHomeEnabled(true); // display icon on actionbar
        getSupportActionBar().setIcon(R.mipmap.ic_launcher); // icon


    } // end method onCreate


    // method to increase the quantity when the (+) button is clicked
    public void increment(View view) {
        // cannot make a selection over 10 cups of coffee
        if (quantity <= 9) {
            quantity = quantity + 1;
            displayQuantity(quantity);
            displayPrice(quantity, hasWhippedCream, hasChocolate);
        } else {
            // shows toast error if customer tries to order more than 10 cups of coffee
            Toast toast = Toast.makeText(getApplicationContext(), getString(
                    R.string.ten_cups), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 10, 0);
            toast.show();
        }

    } // end method increment

    // when customer clicks the (-) button it decreases the quantity
    public void decrement(View view) {
        // cannot make a selection less than 0
        if (quantity >= 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
            displayPrice(quantity, hasWhippedCream, hasChocolate);
        } else {
            // shows toast error if customer tries to order less than 0 coffee
            Toast toast = Toast.makeText(getApplicationContext(), getString(
                    R.string.negative_cups), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 10, 0);
            toast.show();
        }
    } // end method decrement

    // determines if the customers wants to add whipped cream
    public void addWhippedCream(View view) {
        CheckBox whippedcreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        hasWhippedCream = whippedcreamCheckBox.isChecked();
        displayPrice(quantity, hasWhippedCream, hasChocolate);
    } // end method addWhippedCream

    // determines if he customer wants to add chocolate
    public void addChocolate(View view) {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        hasChocolate = chocolateCheckBox.isChecked();
        displayPrice(quantity, hasWhippedCream, hasChocolate);
    } // end method addChocolate

    // Method called when the order button is clicked
    public void submitOrder(String message, EditText nameField, CheckBox whippedCreamCheckBox,
                            CheckBox chocolateCheckBox) {
        TextView confirmTextView = (TextView) findViewById(R.id.order_summary);
        confirmTextView.setText(message);
        Button orderButton = (Button) findViewById(R.id.order_button);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();

    } // end method submitOrder


    // display the quantity if the show quantity textview
    public void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.show_quantity_textView);
        quantityTextView.setText("" + number);
    } // end method display quantity


    // displays the price to the customer on the screen after customer has made selection
    public void displayPrice(int quantity, boolean hasWhippedCream, boolean hasChocolate) {
        double priceChocolate = 1.0;
        double priceWhippedCream = 1.0;
        price = quantity * 5.0;

        // determine the total price with whipped cream
        if (hasWhippedCream) {
            price += priceWhippedCream * quantity;
        }

        // determine the total price with chocolate
        if (hasChocolate) {
            price += priceChocolate * quantity;
        }

        // display price to the customer is the show price text view
        TextView priceTextView = (TextView) findViewById(R.id.show_price_textView);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    } // end method displayPrice


    // display order summary for the customer
    public void displayOrderSummary(View view) {
        createOrderSummary(quantity, price);
    } // end method displayOrderSummary

    // Creates the order summary to be displayed for the customer
    public void createOrderSummary(int quantity, double total) {
        EditText nameField = (EditText)
                findViewById(R.id.name_editText);
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox)
                findViewById(R.id.whipped_cream_checkBox);
        CheckBox chocolateCheckBox = (CheckBox)
                findViewById(R.id.chocolate_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String confirmMessage;
        if (name.trim().length() == 0) {
            // if customer does not input a name it will show a 
            Toast toast = Toast.makeText(getApplicationContext(),
                    getString(R.string.name_required), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 10, 0);
            toast.show();
        } else {
            if (quantity == 0) {
                // if customer does not make a selection it will show a toast error
                Toast toast = Toast.makeText(getApplicationContext(),
                        getString(R.string.make_selection), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 10, 0);
                toast.show();
            } else {
                // order summary that will display when order button is clicked
                confirmMessage = "ORDER SUMMARY:" + "\n" + getString(R.string.name) + " " + name;
                confirmMessage += "\n" + getString(R.string.whippedCream) + " " + hasWhippedCream;
                confirmMessage += "\n" + getString(R.string.chocolate1) + " " + hasChocolate;
                confirmMessage += "\n" + getString(R.string.total) + " " +
                        NumberFormat.getCurrencyInstance().format(price) + "\n" +
                        getString(R.string.thankyou);
                submitOrder(confirmMessage, nameField, whippedCreamCheckBox, chocolateCheckBox);
            }

        }
    } // end method  createOrderSummary

} // end class Coffee

