package com.epam.mjc;

import java.util.*;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature}
     * object. signatureString is a java-like method signature with following parts: 1. access modifier - optional,
     * followed by space: ' ' 2. return type - followed by space: ' ' 3. method name 4. arguments - surrounded with
     * braces: '()' and separated by commas: ',' Each argument consists of argument type and argument name, separated by
     * space: ' '. Examples: accessModifier returnType methodName(argumentType1 argumentName1, argumentType2
     * argumentName2) private void log(String value) Vector3 distort(int x, int y, int z, float magnitude) public
     * DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringSplitter sp = new StringSplitter();
        List<String> splitSignature = sp.splitByDelimiters(signatureString, List.of(",", " "));
        System.out.println(splitSignature);

        StringJoiner sj = new StringJoiner(" ");
        for (String str : splitSignature) {
            sj.add(str);
        }
        String joined = sj.toString();
        if (joined.contains("(")) {
            joined = joined.replace("(", " ").replace(")", "");
        }
        List<String> result = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(joined, " ");
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        System.out.println(result);

        Set<String> validAccessModifiers = new HashSet<>(Arrays.asList("public", "private", "protected"));
        int nameIndex = 1;
        if (validAccessModifiers.contains(result.get(0))) {
            nameIndex = 2;
        }

        System.out.println("name index = " + nameIndex);

        List<MethodSignature.Argument> args = new ArrayList<>();
        for (int i = nameIndex + 1; i < result.size() - 1; i += 2) {
            String type = result.get(i);
            String name = result.get(i + 1);
            args.add(new MethodSignature.Argument(type, name));
        }

        MethodSignature signature = new MethodSignature(result.get(nameIndex), args);

        if (nameIndex == 2) {
            signature.setAccessModifier(result.get(0));
        }
        signature.setReturnType(result.get(nameIndex - 1));

        return signature;
    }
}
