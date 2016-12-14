<?php

ini_set('display_errors', 1);
require_once '../include/DbHandler.php';
require '../libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();


function authenticate(\Slim\Route $route) {
	//add your own authentication methods here
}

function verifyRequiredParams($required_fields) {
	
    $error = false;
    $error_fields = "";
    $request_params = array();
    $request_params = $_REQUEST;
	
    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }
	
    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }

    if ($error) {
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoResponse(400, $response);
        $app->stop();
    }
	
}

function echoResponse($status_code, $response) {
	
    $app = \Slim\Slim::getInstance();
    $app->status($status_code);
    $app->contentType('application/json');
    echo json_encode($response);
	
}

function validateEmail($email) {
    $app = \Slim\Slim::getInstance();
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $response["error"] = true;
        $response["message"] = 'Email address is not valid';
        echoResponse(400, $response);
        $app->stop();
    }
}

/**
 * ----------- METHODS WITHOUT AUTHENTICATION ---------------------------------
 * registration and connecting to database
 */
 
$app->get('/getAll', function() use ($app) {

            $db = new DbHandler();
            $database = $db->getAll();
			$response = array();

            if ($database != NULL) {		           				
				$response["error"] = false;
				$response['database'] = $database;			
            } else {
                // unknown error occurred
                $response['error'] = true;
                $response['message'] = "Please check your network connection and try again.";
            }

            echoResponse(200, $response);
			
        });
		
$app->get('/getStudents', function() use ($app) {

            $db = new DbHandler();
            $database = $db->getStudents();
			$response = array();

            if ($database != NULL) {		           				
				$response["error"] = false;
				$response['database'] = $database;			
            } else {
                // unknown error occurred
                $response['error'] = true;
                $response['message'] = "Please check your network connection and try again.";
            }

            echoResponse(200, $response);
			
        });
$app->post('/delUser', function() use ($app) {

            $response = array();

            // reading post params
         
            $id = $app->request->post('id');
        
            
            $db = new DbHandler();
            $res = $db->delete($id);

            if ($res == 1) {
                $response["error"] = false;
                $response["message"] = "You are successfully registered";
            } else {
                $response["error"] = true;
                $response["message"] = "An error occurred while registereing";
            } 
            // echo json response
            echoResponse(200, $response);
        });		
$app->post('/register', function() use ($app) {
	
            $response = array();

            // reading post params
			$name = $app->request->post('name');
            $user_id = $app->request->post('user_id');

            $email = $app->request->post('email');
			$type = $app->request->post('type');
			$book = $app->request->post('book');
			$date = $app->request->post('date');
            $id = $app->request->post('id');
            $db = new DbHandler();
            $res = $db->createUser($name, $user_id, $email, $type, $book, $date, $id);

            if ($res == 1) {
                $response["error"] = false;
                $response["message"] = "You are successfully registered";
            } else {
                $response["error"] = true;
                $response["message"] = "An error occurred while registereing";
            } 
            // echo json response
            echoResponse(200, $response);
        });
$app->post('/update', function() use ($app) {
    
            $response = array();

            // reading post params
            $name = $app->request->post('name');
            $user_id = $app->request->post('user_id');
            $email = $app->request->post('email');
            $type = $app->request->post('type');
			$book = $app->request->post('book');
			$date = $app->request->post('date');
            $id = $app->request->post('id');

            $response["name"] = $name;
            $response["user_id"] = $user_id;
            $response["email"] = $email;
            $response["type"] = $type;
			$response["book"] = $book;
			$response["date"] = $date;
            $response["id"] = $id;

            $db = new DbHandler();
            $res = $db->updateUser($name, $user_id, $email, $type, $book, $date, $id);

            if ($res == 1) {
                $response["error"] = false;
                $response["message"] = "You are successfully registered";
            } else {
                $response["error"] = true;
                $response["message"] = "An error occurred while registereing";
            } 
            // echo json response
            echoResponse(200, $response);
        });
$app->run();

?>