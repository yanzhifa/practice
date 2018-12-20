#!/bin/sh

# This script fetch the recorded ip from a designated DNS server for a passed hostname.
# Empty string will be returned if no record being searched.

dig @$1 $2 +short
