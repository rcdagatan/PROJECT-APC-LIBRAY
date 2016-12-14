<?php

class DbHandler {

    private $conn;

    function __construct() {
		
        require_once dirname(__FILE__) . '/DbConnect.php';
		require_once '../include/Constants.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
		
    }
	
	public function getAll(){
		
		$stmt = $this->conn->prepare("SELECT 
		
					apc.id, 
					apc.name, 
					apc.id_number, 
					apc.email,
					apc.book,
					apc.date,
					type.type_name 
					
					FROM apc INNER JOIN type ON apc.type = type.id");
		
		$database = array();
        if ($stmt->execute()) {
			$stmt->bind_result($id, $name, $id_number, $email, $book, $date, $type_name);
        
			while($stmt->fetch()){
				array_push($database ,array(
				ID => $id,
				NAME => $name,
				ID_NUMBER => $id_number,
				EMAIL => $email,
				TYPE_NAME => $type_name,
				BOOK => $book,
				DATE => $date					
				));
			}

			$stmt->close();
			return $database;
		
		} else {
            return NULL;
        }
		
	}
	
	public function getStudents(){
		
		$stmt = $this->conn->prepare("SELECT 
		
					apc.id, 
					apc.name, 
					apc.id_number, 
					apc.email, 
					type.type_name 
					
					FROM apc INNER JOIN type ON apc.type = type.id
					
					WHERE apc.type = 1");
		
		$database = array();
        if ($stmt->execute()) {
			$stmt->bind_result($id, $name, $id_number, $email, $type_name);
        
			while($stmt->fetch()){
				array_push($database ,array(
				ID => $id,
				NAME => $name,
				ID_NUMBER => $id_number,
				EMAIL => $email,
				TYPE_NAME => $type_name	
				));
			}

			$stmt->close();
			return $database;
		
		} else {
            return NULL;
        }
		
	}
	
	public function createUser($name, $id_number, $email, $type, $book, $date) {
        
        $response = array();

            // insert query
            $stmt = $this->conn->prepare("INSERT INTO apc(
				name, 
				id_number, 
				email, 
				type,
				book,
				date) 
				
				values(?, ?, ?, ?, ?, ?)");
				
            $stmt->bind_param("ssssss",$name, $id_number, $email, $type, $book, $date);
            $result = $stmt->execute();
            $stmt->close();

            // Check for successful insertion
            if ($result) {
                // User successfully inserted
                return 1;
            } else {
                // Failed to create user
                return 0;
            }
        
        return $response;
    }
    	public function updateUser($name, $id_number, $email, $type, $book, $date, $id) {
        
        $response = array();

            // insert query
            $stmt = $this->conn->prepare("UPDATE apc set apc.name = ?, apc.id_number = ?, apc.email = ? , apc.type = ? , apc.book = ? , apc.date = ? where apc.id =?");

            $stmt->bind_param("sssssss",$name, $id_number, $email, $type, $book, $date, $id);
            $result = $stmt->execute();
            $stmt->close();

            // Check for successful insertion
            if ($result) {
                // User successfully inserted
                return 1;
            } else {
                // Failed to create user
                return 0;
            }
        
        return $response;
    }
	public function delete($id) {
        
        $response = array();

            // insert query
            $stmt = $this->conn->prepare("DELETE FROM apc WHERE apc.id = ?");
            $stmt->bind_param("s",$id);
            $result = $stmt->execute();
            $stmt->close();

            // Check for successful insertion
            if ($result) {
                // User successfully inserted
                return 1;
            } else {
                // Failed to create user
                return 0;
            }
        
        return $response;
    }
}

?>