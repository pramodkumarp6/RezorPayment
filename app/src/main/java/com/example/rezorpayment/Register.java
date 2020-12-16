package com.example.rezorpayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rezorpayment.app.RetrofitClient;
import com.example.rezorpayment.model.citymodel.CityResponse;
import com.example.rezorpayment.model.countrymodel.CountryResponse;
import com.example.rezorpayment.model.gendermodel.GenderResponse;
import com.example.rezorpayment.model.registerModel.RegisterModel;
import com.example.rezorpayment.model.registerModel.Result;
import com.example.rezorpayment.model.statemodel.StateResponse;
import com.example.rezorpayment.model.statemodel.StateSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText editTextname;
    private EditText editTextemail;
    private EditText editTextpassword;
    private EditText lastnameuser, usermobile, userstate, usercity, useraddress;
    private EditText inputOtp;
    private LinearLayout layoutEditMobile;
    private ViewPager viewPager;
    private Button b1, btnVerifyOtp;
    private ProgressDialog progressDialog;
    private Spinner spinnercountry ,spinerstate,spinnercity,spinnerGender;
    private StateSender stateSender;

    ArrayList<String> stateNames = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<String> countryId = new ArrayList<>();
    ArrayList<String> stateId = new ArrayList<>();
    ArrayList<String> citiesId = new ArrayList<>();
    ArrayList<String> citiesName = new ArrayList<>();

    ArrayList<String> genderName = new ArrayList<>();
    ArrayList<String> CitisNames = new ArrayList<>();
    public  String country_id;
    public  String city_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");
        countries();
        gender();

        lastnameuser = findViewById(R.id.userlastname);
        editTextemail = findViewById(R.id.editTextEmail);
        inputOtp =  findViewById(R.id.inputOtp);
        editTextname = findViewById(R.id.textusername);
        editTextpassword = findViewById(R.id.editTextPassword);
        spinnerGender = (Spinner)findViewById(R.id.gender);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        usermobile = findViewById(R.id.userMobile);




        spinnercountry =(Spinner) findViewById(R.id.country);

        spinnercountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 String country_id = countryId.get(position);

                state(country_id);





            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        //  btnVerifyOtp = findViewById(R.id.btn_verify_otp);
        useraddress = findViewById(R.id.userAdd5);
        // layoutEditMobile =  findViewById(R.id.layout_edit_mobile);
        b1 = findViewById(R.id.buttonSignup);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });




        spinerstate =(Spinner)  findViewById(R.id.userAdd3);
        spinerstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String city_id = stateId.get(position);

                city(city_id);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       spinnercity =(Spinner)  findViewById(R.id.userAdd4);


        spinnercity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void register() {
         ProgressDialog progressDialog = new ProgressDialog(this);

        final String name = editTextname.getText().toString().trim();
        final String lastname = lastnameuser.getText().toString().trim();

        final String email = editTextemail.getText().toString().trim();
        final String password = editTextpassword.getText().toString().trim();

        final String mobile = usermobile.getText().toString().trim();
        String  country= spinnercountry.getSelectedItem().toString().trim();
        String  state= spinerstate.getSelectedItem().toString().trim();
        String  city= spinnercity.getSelectedItem().toString().trim();
        String  gender= spinnerGender.getSelectedItem().toString().trim();

        final String address = useraddress.getText().toString().trim();


        if (spinnercountry.getSelectedItem().toString().trim().equalsIgnoreCase("Select Country")) {
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();

            return ;

        }


        if (spinerstate.getSelectedItem().toString().trim().equalsIgnoreCase("Select State")) {
            Toast.makeText(this, "Select State", Toast.LENGTH_SHORT).show();

            return ;

        }


        if (spinnercity.getSelectedItem().toString().trim().equalsIgnoreCase("Select City")) {
            Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();

            return ;

        }




        if (spinnerGender.getSelectedItem().toString().trim().equalsIgnoreCase("Select Gender")) {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();

            return;

        }

        if (name.isEmpty()) {
            editTextname.setError("Name  is required ");
            editTextname.requestFocus();
            return;
        }

       if (mobile.length() < 10)  {
           Toast.makeText(this, "Enter Mobile ", Toast.LENGTH_SHORT).show();
            return;
        }

      /*  if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemail.setError("Enter a valid email");
            editemail.requestFocus();
            return;
        }*/

        if (password.length() < 4) {
            Toast.makeText(this, "Password Enter Four digit", Toast.LENGTH_SHORT).show();
            return;
        }

       /* if (! pass_confirm.equals(password)  )   {
            editconfirm_pass.setError("Confirm is Not match");
            editconfirm_pass.requestFocus();
            return;

        }*/

        if (address.isEmpty()) {
            Toast.makeText(this, "Addres Mandatory", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registration...");
        progressDialog.show();

        RegisterModel register = new RegisterModel(lastname, name, email, password, mobile, gender, country, state, city, address);

        System.out.println("NOIDA" + register.getEmail());

        Log.d("NOIDA", register.getEmail());

        Call<Result> call = RetrofitClient.getInstance().getApi().createUser(register);


        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                Result result = response.body();
                if (result.getStatus() == 200) {

                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void gender() {

        Call<GenderResponse> call = RetrofitClient.getInstance().getApi().gender();

        call.enqueue(new Callback<GenderResponse>() {
            @Override
            public void onResponse(Call<GenderResponse> call, Response<GenderResponse> response) {

                try{
                    GenderResponse genderResponse =response.body();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Log.e("Gender:", gson.toJson(genderResponse));
                    genderName.clear();
                    genderName.add("Select Gender");
                    if(genderResponse.getStatus() == 200){
                        for (int i = 0; i < genderResponse.getData().size(); i++) {

                            genderName.add(genderResponse.getData().get(i).getGenderName());

                        }

                        ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_gender, genderName);
                        dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerGender.setAdapter(dataAdapter12);


                    } else {
                        Toast.makeText(getApplicationContext(), "Gender is  not available", Toast.LENGTH_SHORT).show();
                    }



                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<GenderResponse> call, Throwable t) {

            }
        });

    }

    public void countries() {

        Call<CountryResponse> call = RetrofitClient.getInstance().getApi().country();

        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {



                try {


                    CountryResponse countrymodel = response.body();


                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Log.e("Country:", gson.toJson(countrymodel));
                    countryNames.clear();
                    countryId.clear();
                    countryId.add("125");
                    countryNames.add("Select Country");

                    if (countrymodel.getStatus() == 200) {
                        for (int i = 0; i < countrymodel.getData().size(); i++) {

                            countryNames.add(countrymodel.getData().get(i).getName());

                            countryId.add(countrymodel.getData().get(i).getId());
                            String d =countrymodel.getData().get(i).getId();



                        }


                        ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_country, countryNames);
                        dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnercountry.setAdapter(dataAdapter12);


                    } else {
                        Toast.makeText(getApplicationContext(), "Countris is  not available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {

            }
        });
    }


    public void state(String country_id) {

                Log.d("country_id",country_id);


        Call<StateResponse> call = RetrofitClient.getInstance().getApi().state(country_id);

        call.enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {


                try {


                    StateResponse stateModel = response.body();
                    System.out.println(stateModel);

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Log.e("state:", gson.toJson(stateModel));
                    stateNames.clear();
                    stateId.clear();
                    stateId.add("125");
                    stateNames.add("Select State");

                    if (stateModel.getStatus() == 200) {
                        for (int i = 0; i < stateModel.getData().size(); i++) {

                            stateNames.add(stateModel.getData().get(i).getName());

                            stateId.add(stateModel.getData().get(i).getId());



                        }

                        ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_states, stateNames);
                        dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinerstate.setAdapter(dataAdapter12);


                    } else {
                        Toast.makeText(getApplicationContext(), "state is  not available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {

            }
        });

    }

    public void city(String city_id) {

        Call<CityResponse> call = RetrofitClient.getInstance().getApi().city(city_id);



        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                try {


                    CityResponse stateModel = response.body();


                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Log.e("City:", gson.toJson(stateModel));
                    citiesName.clear();
                    citiesId.clear();
                    citiesId.add("125");
                    citiesName.add("Select City");

                    if (stateModel.getStatus() == 200) {
                        for (int i = 0; i < stateModel.getData().size(); i++) {

                            citiesName.add(stateModel.getData().get(i).getName());

                            citiesId.add(stateModel.getData().get(i).getId());



                        }

                        ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item_cities, citiesName);
                        dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnercity.setAdapter(dataAdapter12);


                    } else {
                        Toast.makeText(getApplicationContext(), "state is  not available", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {

            }
        });
    }


}