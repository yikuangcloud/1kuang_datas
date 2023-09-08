package com.ruoyi.common.exception.user;

public class UserNotExistsException extends UserException{

	private static final long serialVersionUID = 1L;
	
	public UserNotExistsException(){
        super("user.telphone.notbind", null);
    }

}
