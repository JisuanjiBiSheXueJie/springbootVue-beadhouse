package com.example.nursinghomeapplication;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 28159
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.example.nursinghomeapplication.mapper")
public class NursingHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NursingHomeApplication.class, args);
        System.out.println("   ,--,                                                                                                                             \n" +
                ",---.'|                                                                                                                             \n" +
                "|   | :                                                                    ,--,                                                     \n" +
                ":   : |                                        ,--,                      ,--.'|                            ,---,              ,---, \n" +
                "|   ' :     ,---.                            ,--.'|                      |  | :     ,---.                ,---.'|            ,---.'| \n" +
                ";   ; '    '   ,'\\     .---.                 |  |,     .--.--.           :  : '    '   ,'\\               |   | :            |   | : \n" +
                "'   | |__ /   /   |  /.  ./|  ,---.          `--'_    /  /    '          |  ' |   /   /   | ,--.--.      |   | |  ,---.     |   | | \n" +
                "|   | :.'.   ; ,. :.-' . ' | /     \\         ,' ,'|  |  :  /`./          '  | |  .   ; ,. :/       \\   ,--.__| | /     \\  ,--.__| | \n" +
                "'   :    '   | |: /___/ \\: |/    /  |        '  | |  |  :  ;_            |  | :  '   | |: .--.  .-. | /   ,'   |/    /  |/   ,'   | \n" +
                "|   |  ./'   | .; .   \\  ' .    ' / |        |  | :   \\  \\    `.         '  : |__'   | .; :\\__\\/: . ..   '  /  .    ' / .   '  /  | \n" +
                ";   : ;  |   :    |\\   \\   '   ;   /|        '  : |__  `----.   \\        |  | '.'|   :    |,\" .--.; |'   ; |:  '   ;   /'   ; |:  | \n" +
                "|   ,/    \\   \\  /  \\   \\  '   |  / |        |  | '.'|/  /`--'  /        ;  :    ;\\   \\  //  /  ,.  ||   | '/  '   |  / |   | '/  ' \n" +
                "'---'      `----'    \\   \\ |   :    |        ;  :    '--'.     /         |  ,   /  `----';  :   .'   |   :    :|   :    |   :    :| \n" +
                "                      '---\" \\   \\  /         |  ,   /  `--'---'           ---`-'         |  ,     .-./\\   \\  /  \\   \\  / \\   \\  /   \n" +
                "                             `----'           ---`-'                                      `--`---'     `----'    `----'   `----'   ");
    }

}
