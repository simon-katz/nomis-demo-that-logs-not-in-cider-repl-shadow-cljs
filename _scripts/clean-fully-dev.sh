#!/usr/bin/env bash
set -e -x

# From https://stackoverflow.com/a/246128/2148181
SCRIPT_DIR="$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

source "$SCRIPT_DIR/_common.sh"

clean_fully_dev
