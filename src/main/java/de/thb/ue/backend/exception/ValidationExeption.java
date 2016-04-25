/*
 * Copyright 2016 Max Gregor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.thb.ue.backend.exception;

import lombok.Getter;

@Getter
public class ValidationExeption extends Exception {

    private int type;
    public static final int OBJECT_NULL = 1;
    public static final int OBJECT_INVALID = 2;

    public ValidationExeption(int type) {
        this.type = type;
    }
    public ValidationExeption(int type, String message) {
        super(message);
        this.type = type;
    }


    public ValidationExeption(String message) {
        super(message);
    }
}
