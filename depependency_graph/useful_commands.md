mvn com.github.ferstl:depgraph-maven-plugin:4.0.3:aggregate -DgraphFormat=dot -DoutputFile=project-deps.dot

dot -Tpng project-deps.dot -o project-deps.png
