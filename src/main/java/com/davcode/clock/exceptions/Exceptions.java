package com.davcode.clock.exceptions;

public class Exceptions extends RuntimeException{

    public Exceptions(String message){
        super(message);
    }

    public static class UserNotFoundException extends Exceptions{

        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class EmployeeNotFoundException extends Exceptions{

        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }

    public static class ClockNotFoundException extends Exceptions{

        public ClockNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidDataException extends Exceptions{

        public InvalidDataException(String message) {
            super(message);
        }
    }

    public static class UnauthorizedException extends Exceptions{

        public UnauthorizedException(String message) {
            super(message);
        }
    }

    public static class NoClocksException extends Exceptions{
        public NoClocksException(String message) {
            super(message);
        }
    }

    public static class NoAutomaticSchedulingForUser extends Exceptions{

        public NoAutomaticSchedulingForUser(String message) {
            super(message);
        }
    }

    public static class CompanyNotFoundException extends Exceptions{

        public CompanyNotFoundException(String message) {
            super(message);
        }
    }

    public static class ConflictedScheduleException extends Exceptions {
        public ConflictedScheduleException(String message) {
            super(message);
        }
    }

    public static class StartTimeIsAfterEndTimeException extends Exceptions {
        public StartTimeIsAfterEndTimeException(String message) {
            super(message);
        }
    }

    public static class ActiveBreakAlreadyExists extends Exceptions {
        public ActiveBreakAlreadyExists(String message) {
            super(message);
        }
    }
    public static class NoEmployeesInCompany extends Exceptions {
        public NoEmployeesInCompany(String message) {
            super(message);
        }
    }

}
