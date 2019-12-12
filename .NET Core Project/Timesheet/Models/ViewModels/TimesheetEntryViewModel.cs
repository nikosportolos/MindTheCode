using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Models.ViewModels
{
    public class TimesheetEntryViewModel
    {
        public int Id { get; set; }

        public DateTime EntryDate { get; set; }

        [NotNull]
        public string UserId { get; set; }

        [NotNull]
        public int ProjectId { get; set; }

        public int HoursWorked { get; set; }

    }
}
