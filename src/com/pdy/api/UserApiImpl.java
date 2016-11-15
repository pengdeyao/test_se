package com.pdy.api;

public class UserApiImpl implements IUserApi {

    @Override
    public String getUser() {
        System.out.println("调用api");
        // if (1 == 1)
            // throw new RuntimeException();

        return "肖学鹏";
    }

}
