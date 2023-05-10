package member.model;

import java.util.Scanner;
public interface Member {
    String getName();

    default public void hi() {}

//    String authentication();
}