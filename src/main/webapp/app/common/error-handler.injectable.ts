import {inject, Injectable} from '@angular/core';
import {FormGroup, ValidationErrors} from '@angular/forms';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandler {

  router = inject(Router);

  handleServerError(error: ErrorResponse, group?: FormGroup, getMessage?: (key: string) => string) {

    if (!error || !error.fieldErrors) {
      this.router.navigate(['/error'], {
        state: {
          errorStatus: error.status ? '' + error.status : '503'
        }
      });
      return;
    }

    const errorsMap: Record<string, ValidationErrors> = {};
    for (const fieldError of error.fieldErrors) {
      const fieldName = fieldError.property;
      if (!errorsMap[fieldName]) {
        errorsMap[fieldName] = {};
      }
      // look for message under key <fieldName>.<code> or <code>
      // use global error message or error code as fallback
      let errorMessage = getGlobalErrorMessage(fieldError.code) || fieldError.code;
      if (getMessage) {
        errorMessage = getMessage(fieldError.property + '.' + fieldError.code) ||
          getMessage(fieldError.code) || errorMessage;
      }
      errorsMap[fieldName][fieldError.code] = errorMessage;
    }
    // write errors to fields
    for (const [key, value] of Object.entries(errorsMap)) {
      group?.get(key)?.setErrors(value);
    }

  }

}

export function getGlobalErrorMessage(key: string, details?: any) {
  let globalErrorMessages: Record<string, string> = {
    required: $localize`:@@required:Please provide a value.`,
    maxlength: $localize`:@@maxlength:Your value must have a length of less then ${details?.requiredLength} characters.`
  };
  return globalErrorMessages[key];
}

interface FieldError {
  code: string;
  property: string;
  message: string;
  rejectedValue: any | null;
  path: string | null;
}

interface ErrorResponse {
  status: number;
  code: string;
  message: string;
  fieldErrors?: FieldError[];
}

export function createErrNotFoundByProperty(propertyName: string, message: string): ErrorResponse {
  return {
    status: 404,
    code: 'NOT_FOUND',
    message: message,
    fieldErrors: [
      {
        code: 'NOT_FOUND',
        property: propertyName,
        message: message,
        rejectedValue: null,
        path: null
      }
    ]
  }
}
