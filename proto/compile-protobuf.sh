cd $(dirname $0)
PROTO_SRC_DIR=.
DST_CLASSES_DIR=../srv/main/java

protoc --java_out=$DST_CLASSES_DIR $PROTO_SRC_DIR/novedadesRequestBody.proto
